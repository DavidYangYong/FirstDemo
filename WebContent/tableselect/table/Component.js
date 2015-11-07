sap.ui.define([ 'sap/ui/core/UIComponent' ], function(UIComponent) {
	"use strict";

	return UIComponent.extend("sap.ui.table.sample.Menus.Component",
			{
				metadata : {
					rootView : "sap.ui.table.sample.Menus.view.View",
					dependencies : {
						libs : [ "sap.ui.table", "sap.ui.unified", "sap.m" ]
					},

					config : {
						sample : {
							stretch : true,
							files : [ "./view/View.view.xml",
									"Controller.controller.js",
									"TableExampleUtils.js" ]
						}
					}
				}
			});

});
