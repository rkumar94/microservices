// TODO разделить на MVC
function init(ctx) {

    var fromPage = null;

    var showFromPage = function() {
        window.location.hash = fromPage;
        fromPage = null;
    }

    var isConnected = function(url, onConnected) {
        $.get(ctx + "/connected", function(userName) {
            if (userName == "") {
                fromPage = url;
                window.location.hash = '/connect';
            } else {
                if (!!onConnected) {
                    onConnected(userName);
                }
            }
        });
    }

    var show = function(selector) {
        var component = $(selector);
        component.find('.container').children().not(':first').remove();
        component.show();
    }

    var initMenu = function() {
        show('#menu');

        $.get(ctx + "/menu/content", function(elements) {
            $("#loading").hide(300, function() {
                $('#menu script').tmpl(elements).appendTo('#menu .container');
            });
        });
    };

    var initConnect = function() {
        $("#database").val("");
        $("#username").val("");
        $("#password").val("");
        $('#error').hide();
        $('#error').html("");
        $("#loading").hide(300, function() {
            $('#connecting-form').show();
        });
    };

    var initActions = function(userName) {
        isConnected("actions", function(userName) {
            show('#actions');

            $.get(ctx + '/actions/' + userName + '/content', function(elements) {
                $("#loading").hide(300, function() {
                    $('#actions script').tmpl(elements).appendTo('#actions .container');
                });
            });
        });
    };

    var hideAllScreens = function() {
        $('#menu').hide();
        $('#help').hide();
        $('#actions').hide();
        $('#connecting-form').hide();
    }

    var loadPage = function(data) {
        hideAllScreens();
        $("#loading").show();

        var page = data[0];
        if (page == 'menu') {
           initMenu();
        } else if (page == 'connect') {
           initConnect();
        } else if (page == 'actions') {
           initActions(data[1]);
        } else {
           window.location.hash = "/menu";
        }
    }

    var load = function() {
        var hash = window.location.hash.substring(1);
        var parts = hash.split('/');
        if (parts[0] == '') {
            parts.shift();
        }
        loadPage(parts);
    }

    $(window).bind('hashchange', function(event) {
        load();
    });

    $('#connect').click(function() {
        var connection = {};
        connection.database = $("#database").val();
        connection.userName = $("#username").val();
        connection.password = $("#password").val();

        $.ajax({
            url: ctx + "/connect",
            data: connection,
            type: 'PUT',
            success: function(message) {
                if (message == "" || message == null) {
                    showFromPage();
                } else {
                    $('#error').html(message);
                    $('#error').show();
                }
            }
        });
    });

    load();
}
