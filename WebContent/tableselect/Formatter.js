sap.ui.define(function() {
	"use strict";

	var Formatter = {

		weightState : function(fValue) {
			try {
				fValue = parseFloat(fValue);
				if (fValue < 0) {
					return "None";
				} else if (fValue < 1000) {
					return "Success";
				} else if (fValue < 2000) {
					return "Warning";
				} else {
					return "Error";
				}
			} catch (err) {
				return "None";
			}
		},
		listProductsSelected : function(oContext) {
			var mOrder = oContext.getModel("Order").getData();
			var oModel = oContext.getModel();
			return Object.keys(mOrder.products).filter(function(sKey) {
				return mOrder.products[sKey];
			}).map(function(sKey) {
				return oModel.getProperty(sKey);
			});
		}
	};

	return Formatter;

}, /* bExport= */true);
