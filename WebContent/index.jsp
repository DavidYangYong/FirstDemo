<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='X-UA-Compatible' content='IE=edge' />
<title>Hello World</title>
<script id='sap-ui-bootstrap' src='resources/sap-ui-core.js'
	data-sap-ui-theme='sap_goldreflection'
	data-sap-ui-libs='sap.ui.commons, sap.ui.table,sap.ui.ux3'
	type="text/javascript"></script>
<script>
	//create the toolbar button
	var selectBtn = new sap.ui.commons.Button({

		text : "Button is show select row value",
		press : function() {
			alert("Button pressed!");
			var i = oTable.getSelectedIndex();
			if (i == -1) {
				alert("Please Select a row to Update");
				return;

			} else if (i >= 0) {
				var selectedRow = oTable.getRows()[i];
				var selectContext = oTable.getContextByIndex(i);

				var bindingPath = selectContext.sPath;//获得绑定路径
				alert(bindingPath);
				var selectValue = selectContext.getProperty(bindingPath
						+ "/lastName");
				//	var selectValue=selectModel.getProperty("lastName");
				console.log(selectValue);
				sap.ui.commons.MessageBox.alert(selectValue);
				// 				var cells = selectedRow.getCells();
				// 				for (var n = 0;  n < cells.length; n++) {
				// 					var valText=cells[n].getValue();
				// 					sap.ui.commons.MessageBox.alert("show message","");
				// 				}
			}

		}
	});

	var updateBtn = new sap.ui.commons.Button(
			{

				text : "更新",
				press : function() {
					//alert("Button pressed!");
					var i = oTable.getSelectedIndex();
					if (i == -1) {
						alert("Please Select a row to Update");
						return;

					} else if (i >= 0) {
						var selectedRow = oTable.getRows()[i];
						var selectContext = oTable.getContextByIndex(i);

						var bindingPath = selectContext.sPath;//获得绑定路径
						//	alert(bindingPath);
						var lastName = selectContext.getProperty(bindingPath
								+ "/lastName");
						console.log(lastName);
						//	sap.ui.commons.MessageBox.alert(lastName);
						/**
						 *创建Dialog start
						 *
						 */
						var oDialogu = new sap.ui.commons.Dialog("Dialogu", {

							modal : true,

							closed : function(oControlEvent) {

								sap.ui.getCore().getElementById('Dialogu')
										.destroy();

							}
						});
						oDialogu.setTitle("Update Bank Collection");
						var oSimpleForm = new sap.ui.layout.form.SimpleForm(
								"formId");
						var oLayout = new sap.ui.commons.layout.MatrixLayout({

							columns : 2,

							width : "100%"

						});

						var tLastName = new sap.ui.commons.TextField(
								"tlastName", {

									tooltip : 'lastName',

									editable : false,

									value : lastName,

									width : '200px',

									required : true

								});

						var oLabel = new sap.ui.commons.Label("lbankCountry", {

							text : 'Bank Country',

							labelFor : tLastName

						});

						oLayout.createRow(oLabel, tLastName);

						oTF = new sap.ui.commons.TextField("tbankID", {

							tooltip : 'Bank ID',

							editable : false,

							required : true,

							width : '200px',

							value : ""

						});

						oLabel = new sap.ui.commons.Label("lbankID", {

							text : 'Bank ID',

							labelFor : oTF

						});

						oLayout.createRow(oLabel, oTF);

						oTF = new sap.ui.commons.TextField("tbankName", {

							tooltip : 'Bank Name',

							editable : true,

							required : true,

							width : '200px',

							value : ""

						});

						oLabel = new sap.ui.commons.Label("lbankName", {

							text : 'Bank Name',

							labelFor : oTF

						});

						oLayout.createRow(oLabel, oTF);

						oTF = new sap.ui.commons.TextField("tregion", {

							tooltip : 'Region Name',

							maxLength : 3,

							editable : true,

							value : "",

							width : '200px'

						});

						oLabel = new sap.ui.commons.Label("lregion", {

							text : 'Region Name',

							labelFor : oTF

						});

						oLayout.createRow(oLabel, oTF);

						oTF = new sap.ui.commons.TextField("tstreet", {

							tooltip : 'Street Name',

							editable : true,

							width : '200px',

							value : ""

						});

						oLabel = new sap.ui.commons.Label("lstreet", {

							text : 'Street Name',

							labelFor : oTF

						});

						oLayout.createRow(oLabel, oTF);

						oTF = new sap.ui.commons.TextField("tcity", {

							tooltip : 'City Name',

							editable : true,

							value : "",

							width : '200px'

						});

						oLabel = new sap.ui.commons.Label("lcity", {

							text : 'City Name',

							labelFor : oTF

						});

						oLayout.createRow(oLabel, oTF);
						oSimpleForm.addContent(oLayout);
						oDialogu.addContent(oSimpleForm);
						var uUpdateBtn = new sap.ui.commons.Button(
								{
									text : "Update",
									press : function() {
										var lastName_var = tLastName.getValue();
										//使用$.post方式      
										var url = "${pageContext.request.contextPath}/login.do";
										var jsonModelUpdate = new sap.ui.model.json.JSONModel();
										jsonModelUpdate.setData({
											lastName : lastName_var
										});
										var params = jsonModelUpdate.getJSON();
										//alert(params);
										$.post(

										url, //服务器要接受的url  

										{
											strJson : params
										}, //传递的参数       

										function cbf(result) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  

											var msg;
											if (result.error == null) {
												msg = result.msg;
											} else {
												msg = result.error;
											}
											sap.ui.commons.MessageBox.alert("操作成功",function(){
												oDialogu.close();
											});
											
										},

										'json' //数据传递的类型  json  

										);
									}

								});
						oDialogu.addButton(uUpdateBtn);
						oDialogu.open();
						/**
						 *end
						 */

					}

				}
			});

	// create the DataTable control  
	var oTable = new sap.ui.table.Table({
		id : "tableId",
		title : "Table Example",
		visibleRowCount : 7,
		firstVisibleRow : 3,
		selectionMode : sap.ui.table.SelectionMode.Single,
		selectionBehavior : sap.ui.table.SelectionBehavior.RowSelector,
		toolbar : new sap.ui.commons.Toolbar({
			items : [ selectBtn, updateBtn ]
		})
	});
	// define the Table columns  
	var oControl = new sap.ui.commons.TextView({
		text : "{lastName}"
	}); // short binding notation  
	oTable.addColumn(new sap.ui.table.Column({
		label : new sap.ui.commons.Label({
			text : "Last Name"
		}),
		template : oControl,
		sortProperty : "lastName",
		filterProperty : "lastName",
		width : "100px"
	}));
	oControl = new sap.ui.commons.TextField().bindProperty("value", "name"); // more verbose binding notationt  
	oTable.addColumn(new sap.ui.table.Column({
		label : new sap.ui.commons.Label({
			text : "First Name"
		}),
		template : oControl,
		sortProperty : "name",
		filterProperty : "name",
		width : "80px"
	}));
	oControl = new sap.ui.commons.CheckBox({
		checked : "{checked}"
	});
	oTable.addColumn(new sap.ui.table.Column({
		label : new sap.ui.commons.Label({
			text : "Checked"
		}),
		template : oControl,
		sortProperty : "checked",
		filterProperty : "checked",
		width : "75px",
		hAlign : "Center"
	}));
	oControl = new sap.ui.commons.Link({
		text : "{linkText}",
		href : "{href}"
	});
	oTable.addColumn(new sap.ui.table.Column({
		label : new sap.ui.commons.Label({
			text : "Web Site"
		}),
		template : oControl,
		sortProperty : "linkText",
		filterProperty : "linkText"
	}));
	oControl = new sap.ui.commons.RatingIndicator({
		value : "{rating}"
	});
	oTable.addColumn(new sap.ui.table.Column({
		label : new sap.ui.commons.Label({
			text : "Rating"
		}),
		template : oControl,
		sortProperty : "rating",
		filterProperty : "rating"
	}));

	// create some local data  
	var aData = [ {
		lastName : "Dente",
		name : "Al",
		checked : true,
		linkText : "www.sap.com",
		href : "http://www.sap.com",
		rating : 4
	}, {
		lastName : "Friese",
		name : "Andy",
		checked : true,
		linkText : "https://experience.sap.com/fiori",
		href : "https://experience.sap.com/fiori",
		rating : 2
	}, {
		lastName : "Mann",
		name : "Anita",
		checked : false,
		linkText : "http://www.saphana.com/",
		href : "http://www.saphana.com/",
		rating : 3
	} ];

	// create a JSONModel, fill in the data and bind the Table to this model  
	var oModel = new sap.ui.model.json.JSONModel();
	oModel.setData({
		modelData : aData
	});
	oTable.setModel(oModel);
	oTable.bindRows("/modelData");

	// finally place the Table into the UI  
	oTable.placeAt("content");
</script>
</head>
<body class='sapUiBody'>
	<div id='content'></div>
</body>
</html>
