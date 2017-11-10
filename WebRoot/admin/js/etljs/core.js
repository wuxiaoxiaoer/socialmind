$( document ).ajaxStart(function() {
    NProgress.start();
});

$( document ).ajaxComplete(function() {
    NProgress.done();
});

$(document).ajaxSuccess(function() {
    NProgress.done();
});


Core = {
    init:function(){
        $(document).ready(function(){
            cPlugins.initAll();
            Fjax.dedectModal();
            dTable.dedectTable();
            Fjax.dedectConfirm();

            $( '#dl-menu' ).dlmenu();


        });
    },
    getApiPost:function(route,data,callback){
        $.ajax({
            url:route,
            type :"POST",
            data:data,
            success:function(deger){callback(deger);}
        });
    },
    getApiGet:function(route,data,callback){
        $.ajax({
            url:route,
            type :"GET",
            data:data,
            success:function(deger){callback(deger);}
        });
    },
    createID:function(){
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for( var i=0; i < 5; i++ )
            text += possible.charAt(Math.floor(Math.random() * possible.length));

        return text;
    }
}


cPlugins = {
    initAll:function(){
        cPlugins.initChosen();
        cPlugins.initDatePicker();
        cPlugins.initAutoComplete();
        cPlugins.initMask();
        cPlugins.initTipsy();
        cPlugins.initChecbox();
    },

    initChecbox:function(){
        $(".checkboxcustom").each(function(){$(this).checkbox();});
        $(".checkboxcustom1").each(function(){$(this).checkbox(
            {
                buttonStyle: 'btn-base',
                buttonStyleChecked: 'btn-success',
                checkedClass: 'icon-check',
                uncheckedClass: 'icon-check-empty'
            }
        );});
        $(".checkboxcustom2").each(function(){$(this).checkbox({buttonStyle: 'btn-danger',buttonStyleChecked: 'btn-success',checkedClass: 'icon-check',uncheckedClass: 'icon-check-empty'});});
    },
    initChosen:function(){
        $(".select").each(function(){
            $(this).chosen();
        });
    },
    initMask:function(){
        $(".tarih").mask("99/99/9999");
        $(".telefon").mask("(999) 999-9999");
    },

    initDatePicker:function(){
        $(".dpform").each(function(){
            $(this).datepicker({
                format: 'mm/dd/yyyy'
            });
        });
    },
    initAutoComplete:function(){
        $(".autocomplete").attr('autocomplete', 'off');
        $('.autocomplete').each(function(){
            var source = $(this).attr("data-source");
            if (typeof ac_siteURL != 'undefined') {
                source = ac_siteURL+ source;
            }
            if(source){
                $(this).typeahead({
                    source: function (query, process) {
                        return $.get(source, { query: query }, function (data) {
                            if(query.length<=1){window.disdata='';}
                            if(!window.disdata){
                                window.disdata=data;
                                return process(data.options);
                            }
                        });
                    }
                });
            }else{
                console.warn("autocomplete için data-source belirtilmemiş");
            }
        });
    },
    initTipsy:function(){
        $(".tipUst").tipsy({gravity: 's'});
        $(".tipAlt").tipsy({gravity: 'n'});
        $(".tipSol").tipsy({gravity: 'e'});
        $(".tipSag").tipsy({gravity: 'w'});
    }
}

Fjax = {
    dedectModal:function(){
        $("a.modalfjax").each(function(){
            $(this).bind("click",function(){
                Core.getApiGet($(this).attr("href"),{type:'modal'},function(deger){
                    $("#temp").html(deger);
                    cPlugins.initAll();
                    dTable.dedectTableModal();
                });
                return false;
            });
        });

    },
    dedectConfirm:function(){
        $("a.confirm").each(function(){
            $(this).bind("click",function(){
                var confirmMessage = $(this).attr("data-message");
                if(confirmMessage){
                    if(confirm(confirmMessage)==true){
                        location.href=$(this).attr("href");
                    }else{
                        return false;
                    }
                }
                return false;
            });
        });
    }
}

dTable = {

    dedectTable:function(){
        dTable.dedectTableConsole(".dtable");
    },
    dedectTableModal:function(){
        dTable.dedectTableConsole(".modal-dialog .dtable");
    },
    dedectTableConsole:function(className){
        $(className).each(function(){
            var isAjax = $(this).attr("data-table-ajax");
            var source = $(this).attr("data-source");
            var seriColuns = $(this).attr("data-columns");
            var seriVals= $(this).attr("data-vals");
            var customWhere= $(this).attr("data-customwhere");

            if (typeof siteURL != 'undefined'){
                var setting = {
                    "oLanguage": {
                        "sUrl": siteURL+'datatable/getlanguagedata'
                    },
                    "iDisplayLength": 50
                };
            }

            if(source){
                setting = {
                    "bProcessing": true,
                    "bServerSide": true,
                    "bDestroy": true,
                    "sAjaxSource": source,
                    "fnServerData": function (sSource, aoData, fnCallback) {
                        aoData.push({"name":"columns","value":seriColuns});
                        aoData.push({"name":"columnsval","value":seriVals});
                        aoData.push({"name":"customwhere","value":customWhere});
                        $.ajax({
                            "dataType": "json",
                            "type": "POST",
                            "url": sSource,
                            "data": aoData,
                            "success": fnCallback
                        }).done(function(){
                            cPlugins.initAll();
                            Fjax.dedectConfirm();
                        });
                    },
                    "oLanguage": {
                        "sUrl": siteURL+'datatable/getlanguagedata'
                    },
                    "iDisplayLength": 50
                };
            }
            var Dtablem = $(this).dataTable(setting);
            //dTable.changeContentFilter(Dtablem);
        });
    },
    changeContentFilter:function(oTable){
        alert('merba');
        $(".tfoot").each( function ( i ) {
            alert('selmai');
            this.innerHTML = fnCreateSelect( oTable.fnGetColumnData(i) );
            $('select', this).change( function () {
                oTable.fnFilter( $(this).val(), i );
            } );
        } );
        cPlugins.initAll();
    }
}

Core.init();