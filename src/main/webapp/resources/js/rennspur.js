var navbar_initialized = false;

$(document).ready(
        function () {
            window_width = $(window).width();

            // check if there is an image set for the sidebar's background
            lbd.checkSidebarImage();

            // Init navigation toggle for small screens
            if (window_width <= 991) {
                lbd.initRightMenu();
            }

            // Activate the switches with icons
            if ($('.switch').length != 0) {
                $('.switch')['bootstrapSwitch']();
            }
            // Activate regular switches
            if ($("[data-toggle='switch']").length != 0) {
                $("[data-toggle='switch']").wrap('<div class="switch" />')
                        .parent().bootstrapSwitch();
            }

            $('.nav-tabs a #top').tab('show');
        });

// activate collapse right menu when the windows is resized
$(window).resize(function () {
    if ($(window).width() <= 991) {
        lbd.initRightMenu();
    }
});

lbd = {
    misc : {
        navbar_menu_visible : 0
    },

    checkSidebarImage : function () {
        $sidebar = $('.sidebar');
        image_src = $sidebar.data('image');

        if (image_src !== undefined) {
            sidebar_container = '<div class="sidebar-background" style="background-image: url('
                    + image_src + ') "/>'
            $sidebar.append(sidebar_container);
        }
    },
    initRightMenu : function () {
        if (!navbar_initialized) {
            $navbar = $('nav').find('.navbar-collapse').first().clone(true);

            $sidebar = $('.sidebar');
            sidebar_color = $sidebar.data('color');

            $logo = $sidebar.find('.logo').first();
            logo_content = $logo[0].outerHTML;

            ul_content = '';

            $navbar.attr('data-color', sidebar_color);

            // add the content from the sidebar to the right menu
            content_buff = $sidebar.find('.nav').html();
            ul_content = ul_content + content_buff;

            // add the content from the regular header to the right menu
            $navbar.children('ul').each(function () {
                content_buff = $(this).html();
                ul_content = ul_content + content_buff;
            });

            ul_content = '<ul class="nav navbar-nav">' + ul_content + '</ul>';

            navbar_content = logo_content + ul_content;

            $navbar.html(navbar_content);

            $('body').append($navbar);

            background_image = $sidebar.data('image');
            if (background_image != undefined) {
                $navbar.css('background', "url('" + background_image + "')")
                        .removeAttr('data-nav-image').addClass('has-image');
            }

            $toggle = $('.navbar-toggle');

            $navbar.find('a').removeClass('btn btn-round btn-default');
            $navbar
                    .find('button')
                    .removeClass(
                            'btn-round btn-fill btn-info btn-primary btn-success btn-danger btn-warning btn-neutral');
            $navbar.find('button').addClass('btn-simple btn-block');

            $toggle.click(function () {
                if (lbd.misc.navbar_menu_visible == 1) {
                    $('html').removeClass('nav-open');
                    lbd.misc.navbar_menu_visible = 0;
                    $('#bodyClick').remove();
                    setTimeout(function () {
                        $toggle.removeClass('toggled');
                    }, 400);

                } else {
                    setTimeout(function () {
                        $toggle.addClass('toggled');
                    }, 430);

                    div = '<div id="bodyClick"></div>';
                    $(div).appendTo("body").click(function () {
                        $('html').removeClass('nav-open');
                        lbd.misc.navbar_menu_visible = 0;
                        $('#bodyClick').remove();
                        setTimeout(function () {
                            $toggle.removeClass('toggled');
                        }, 400);
                    });

                    $('html').addClass('nav-open');
                    lbd.misc.navbar_menu_visible = 1;

                }
            });
            navbar_initialized = true;
        }
    }
}

// Returns a function, that, as long as it continues to be invoked, will not
// be triggered. The function will be called after it stops being called for
// N milliseconds. If `immediate` is passed, trigger the function on the
// leading edge, instead of the trailing.

function debounce(func, wait, immediate) {
    var timeout;
    return function () {
        var context = this, args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(function () {
            timeout = null;
            if (!immediate)
                func.apply(context, args);
        }, wait);
        if (immediate && !timeout)
            func.apply(context, args);
    };
};

// primefaces calendar localization (see
// https://code.google.com/archive/p/primefaces/wikis/PrimeFacesLocales.wiki )
PrimeFaces.locales['de'] = {
    closeText : 'Schließen',
    prevText : 'Zurück',
    nextText : 'Weiter',
    monthNames : [ 'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli',
            'August', 'September', 'Oktober', 'November', 'Dezember' ],
    monthNamesShort : [ 'Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug',
            'Sep', 'Okt', 'Nov', 'Dez' ],
    dayNames : [ 'Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag',
            'Freitag', 'Samstag' ],
    dayNamesShort : [ 'So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa' ],
    dayNamesMin : [ 'So', 'Mo', 'Di', 'Mi ', 'Do', 'Fr ', 'Sa' ],
    weekHeader : 'Woche',
    firstDay : 1,
    isRTL : false,
    showMonthAfterYear : false,
    yearSuffix : '',
    timeOnlyTitle : 'Nur Zeit',
    timeText : 'Zeit',
    hourText : 'Stunde',
    minuteText : 'Minute',
    secondText : 'Sekunde',
    currentText : 'Aktuelles Datum',
    ampm : false,
    month : 'Monat',
    week : 'Woche',
    day : 'Tag',
    allDayText : 'Ganzer Tag'
};
PrimeFaces.locales['de_DE'] = PrimeFaces.locales['de'];

PrimeFaces.locales['pl'] = {
    closeText : 'Zamknij',
    prevText : 'Poprzedni',
    nextText : 'Następny',
    monthNames : [ 'Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec',
            'Lipiec', 'Sierpień', 'Wrzesień', 'Październik', 'Listopad',
            'Grudzień' ],
    monthNamesShort : [ 'Sty', 'Lut', 'Mar', 'Kwi', 'Maj', 'Cze', 'Lip', 'Sie',
            'Wrz', 'Paź', 'Lis', 'Gru' ],
    dayNames : [ 'Niedziela', 'Poniedziałek', 'Wtorek', 'Środa', 'Czwartek',
            'Piątek', 'Sobota' ],
    dayNamesShort : [ 'Nie', 'Pon', 'Wt', 'Śr', 'Czw', 'Pt', 'So' ],
    dayNamesMin : [ 'N', 'P', 'W', 'Ś', 'Cz', 'P', 'S' ],
    weekHeader : 'Tydzień',
    firstDay : 1,
    isRTL : false,
    showMonthAfterYear : false,
    yearSuffix : 'r',
    timeOnlyTitle : 'Tylko czas',
    timeText : 'Czas',
    hourText : 'Godzina',
    minuteText : 'Minuta',
    secondText : 'Sekunda',
    currentText : 'Teraz',
    ampm : false,
    month : 'Miesiąc',
    week : 'Tydzień',
    day : 'Dzień',
    allDayText : 'Cały dzień'
};

PrimeFaces.locales['tr'] = {
    closeText : 'kapat',
    prevText : 'geri',
    nextText : 'ileri',
    currentText : 'bugün',
    monthNames : [ 'Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran',
            'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık' ],
    monthNamesShort : [ 'Oca', 'Şub', 'Mar', 'Nis', 'May', 'Haz', 'Tem', 'Ağu',
            'Eyl', 'Eki', 'Kas', 'Ara' ],
    dayNames : [ 'Pazar', 'Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma',
            'Cumartesi' ],
    dayNamesShort : [ 'Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct' ],
    dayNamesMin : [ 'Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct' ],
    weekHeader : 'Hf',
    firstDay : 1,
    isRTL : false,
    showMonthAfterYear : false,
    yearSuffix : '',
    timeOnlyTitle : 'Zaman Seçiniz',
    timeText : 'Zaman',
    hourText : 'Saat',
    minuteText : 'Dakika',
    secondText : 'Saniye',
    ampm : false,
    month : 'Ay',
    week : 'Hafta',
    day : 'Gün',
    allDayText : 'Tüm Gün'
};
