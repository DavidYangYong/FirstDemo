<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Datepicker for Bootstrap1</title>
    <script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
    <link id="bs-css" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link id="bsdp-css" href="${pageContext.request.contextPath }/bootstrap-datepicker/css/bootstrap-datepicker3.css"  rel="stylesheet">
    <script>
      var page = {bootstrap:3};
      function swap_bs(){
        var bscss = $('#bs-css'),
            bsdpcss = $('#bsdp-css');
        if (page.bootstrap == 3){
          bscss.prop('href', '//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css');
          bsdpcss.prop('href', '${pageContext.request.contextPath }/bootstrap-datepicker/css/bootstrap-datepicker.css');
          page.bootstrap = 2;
        }
        else{
          bscss.prop('href', '//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css');
          bsdpcss.prop('href', '${pageContext.request.contextPath }/bootstrap-datepicker/css/bootstrap-datepicker3.css');
          page.bootstrap = 3;
        }

        $(page).trigger('change:bootstrap', page.bootstrap);
      }
    </script>
    <style>
      body {
        padding-top: 50px;
      }
    </style>
<!--     <link href="google-code-prettify/prettify.css" rel="stylesheet"> -->
<!--     <link href="docs.css" rel="stylesheet"> -->
    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
       <script src="${pageContext.request.contextPath }/bootstrap-datepicker/js/prettify.min.js"></script>
    <script src="${pageContext.request.contextPath }/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

    <script src="${pageContext.request.contextPath }/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>
  <SCRIPT type="text/javascript">
  $(function(){
  $('#sandbox-container .input-group.date').datepicker({
      clearBtn: true,
      language: "zh-CN",
      daysOfWeekDisabled: "0,6",
      daysOfWeekHighlighted: "0,6",
      datesDisabled: ['2016-03-29', '2016-03-30']
  });
  });
  </SCRIPT>
  </head>

  <body>
 <div class="span5 col-md-5" id="sandbox-container">
      <div class="input-group date">
      <input type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
    </div>
    </div>
  </body>
</html>
