sap.ui
		.define(
				[ "sap/ui/core/mvc/Controller",
						"sap/ui/table/sample/Menus/TableExampleUtils",
						"sap/ui/model/json/JSONModel", "sap/ui/unified/Menu",
						"sap/ui/unified/MenuItem", "sap/m/MessageToast",
						'sap/m/sample/TableSelectDialog/Formatter',
						'sap/ui/model/Filter' ],
				function(Controller, TableExampleUtils, JSONModel, Menu,
						MenuItem, MessageToast, Formatter, Filter) {
					"use strict";

					return Controller
							.extend(
									"sap.ui.table.sample.Menus.Controller",
									{

										onInit : function() {
											var oView = this.getView();

											// set explored app's demo model on
											// this sample
											var oJSONModel = new sap.ui.model.json.JSONModel();
											oJSONModel.setData(null);
											// TableExampleUtils
											// .initSampleDataModel();
											oView.setModel(oJSONModel);

											oView
													.setModel(
															new JSONModel(
																	{
																		showVisibilityMenuEntry : false,
																		showFreezeMenuEntry : false,
																		enableCellFilter : false
																	}), "ui");
										},

										onColumnSelect : function(oEvent) {
											var oCurrentColumn = oEvent
													.getParameter("column");
											var oImageColumn = this.getView()
													.byId("image");
											if (oCurrentColumn != oImageColumn) {
												return;
											}

											// Just skip opening the column Menu
											// on column "Image"
											oEvent.preventDefault();
										},

										onProductIdCellContextMenu : function(
												oEvent) {
											if (sap.ui.Device.support.touch) {
												return; // Do not use context
												// menus on touch
												// devices
											}

											if (oEvent.getParameter("columnId") != this
													.getView().createId(
															"productId")) {
												return; // Custom context menu
												// for product id column
												// only
											}

											oEvent.preventDefault();

											var oRowContext = oEvent
													.getParameter("rowBindingContext");

											if (!this._oIdContextMenu) {
												this._oIdContextMenu = new Menu();
												this.getView().addDependent(
														this._oIdContextMenu);
											}

											this._oIdContextMenu.destroyItems();
											this._oIdContextMenu
													.addItem(new MenuItem(
															{
																text : "My Custom Cell Action",
																select : function(
																		oEvent) {
																	MessageToast
																			.show("Context action triggered on Column 'Product ID' on id '"
																					+ oRowContext
																							.getProperty("ProductId")
																					+ "'.");
																}.bind(this)
															}));

											// Open the menu on the cell
											var oCellDomRef = oEvent
													.getParameter("cellDomRef");
											var eDock = sap.ui.core.Popup.Dock;
											this._oIdContextMenu.open(false,
													oCellDomRef,
													eDock.BeginTop,
													eDock.BeginBottom,
													oCellDomRef, "none none");
										},

										onQuantityCustomItemSelect : function(
												oEvent) {
											alert("Some custom action triggered on column 'Quantity'.");
										},

										onQuantitySort : function(oEvent) {
											var bAdd = oEvent
													.getParameter("ctrlKey") === true;
											var oColumn = this.getView().byId(
													"quantity");
											var sOrder = oColumn.getSortOrder() == "Ascending" ? "Descending"
													: "Ascending";

											this.getView().byId("table").sort(
													oColumn, sOrder, bAdd);
										},

										showInfo : function(oEvent) {
											TableExampleUtils
													.showInfo(
															jQuery.sap
																	.getModulePath(
																			"sap.ui.table.sample.Menus",
																			"/info.json"),
															oEvent.getSource());
										},
										onExit : function() {
											if (this._oDialog) {
												this._oDialog.destroy();
											}
										},

										handleTableSelectDialogPress : function(
												oEvent) {
											if (!this._oDialog) {
												this._oDialog = sap.ui
														.xmlfragment(
																"sap.m.sample.TableSelectDialog.Dialog",
																this);
											}

											var oJSONModel = TableExampleUtils
													.initSampleDataModel();
											this._oDialog.setModel(oJSONModel);
											// Multi-select if required
											var bMultiSelect = !!oEvent
													.getSource().data("multi");
											this._oDialog
													.setMultiSelect(bMultiSelect);

											// Remember selections if required
											var bRemember = !!oEvent
													.getSource().data(
															"remember");
											this._oDialog
													.setRememberSelections(bRemember);

											this.getView().addDependent(
													this._oDialog);

											// toggle compact style
											jQuery.sap.syncStyleClass(
													"sapUiSizeCompact", this
															.getView(),
													this._oDialog);
											this._oDialog.open();
										},
										_updateOrder : function(oProduct) {
											var oOrderModel = this.getView()
													.getModel();
											var oCartData = oOrderModel
													.getData();
											if (oCartData == null) {
												oOrderModel
														.setData({
															ProductCollection : oProduct
														});
											} else {
												var aCartEntries = oCartData.ProductCollection;

												// var oEntry = null;
												// for (var i = 0; i <
												// aCartEntries.length; i++) {
												// if (aCartEntries[i].ProductId
												// === oProduct.ProductId) {
												// oEntry = aCartEntries[i];
												// break;
												// }
												// }

												// if (oEntry === null) {
												// create new entry
												// oEntry = {
												// Id : jQuery.sap.uid(),
												// Quantity : 1,
												// Name : oProduct.Name,
												// ProductId :
												// oProduct.ProductId,
												// ProductName :
												// oProduct.Name,
												// Price : oProduct.Price,
												// SupplierName :
												// oProduct.SupplierName,
												// Status : oProduct.status,
												// Weight : oProduct.Weight,
												// PictureUrl :
												// oProduct.PictureUrl
												// };
												// oCartData.ProductCollection[oCartData.ProductCollection.length]
												// = oProduct;

												// } else {
												// update existing entry
												// oEntry.Quantity += 1;
												// }

												for (var i = 0; i < oProduct.length; i++) {
													aCartEntries
															.push(oProduct[i]);
												}

												oOrderModel.updateBindings();

												// oOrderModel.setData(oCartData);
												// oOrderModel
												// .setData({
												// ProductCollection : oProduct
												// });

											}

											var str = oOrderModel.getJSON();
											// this.getView().setModel(
											// oOrderModel, "Order");
											// var aProductsSelected =
											// oSelectionInfo;
											// oOrderModel
											// .setData(
											// {
											// count : aProductsSelected.length,
											// hasCounts :
											// aProductsSelected.length > 0
											// }, true);
										},
										handleSearch : function(oEvent) {
											var sValue = oEvent
													.getParameter("value");
											var oFilter = new Filter(
													"Name",
													sap.ui.model.FilterOperator.Contains,
													sValue);
											var oBinding = oEvent.getSource()
													.getBinding("items");
											oBinding.filter([ oFilter ]);
										},

										handleClose : function(oEvent) {
											var aContexts = oEvent
													.getParameter("selectedContexts");
											if (aContexts.length) {
												// MessageToast
												// .show("You have chosen "
												// + aContexts
												// .map(
												// function(
												// oContext) {
												// return oContext
												// .getObject().Name;
												// })
												// .join(
												// ", "));
												var oView = this.getView();
												var oSelectionInfo = {};
												var bSelected = oEvent
														.getParameter("selectedItems");

												var oModel = oEvent.getSource();

												var oTable = this
														.byId("table1");

												if (bSelected.length > 0) {
													for (var i = 0; i < bSelected.length; i++) {
														var id = bSelected[i]
																.getId();
														// oSelectionInfo[bSelected[i]
														// .getBindingPath()] =
														// bSelected[i];
														// var name =
														// bSelected[i]
														// getBindingInfo("Name");
													}

													oSelectionInfo = aContexts
															.map(function(
																	oContext) {
																return oContext
																		.getObject();
															});

												}
												// var sPath = oEvent
												// .getParameter(
												// "listItem")
												// .getBindingContext()
												// .getPath();
												// oSelectionInfo[aContexts
												// .map(
												// function(
												// oContext) {
												// return oContext
												// .getObject();
												// })
												// .getBindingContext()
												// .getPath()] = bSelected;
												//
												this
														._updateOrder(oSelectionInfo);
												// set explored app's demo model
												// on
												// this sample

											}
											oEvent.getSource().getBinding(
													"items").filter([]);

										}

									});

				});
