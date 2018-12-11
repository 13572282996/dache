/*轮播*/
$(function(){

        $('.banner .show ul li:eq(0)').show();

        var speed = 5000,iNum = 0;



        $('.banner .focus a').bind('click',function(){

                var aIndex = $('.banner .focus a').index(this);

                $('.on').removeClass('on');

                $(this).addClass('on');

                $('.banner .show ul li').fadeOut().eq(aIndex).fadeIn();

                iNum = aIndex;

                })

        var autoPlay = function(){

            iNum++;

            if(iNum == $('.banner .show ul li').length){iNum=0;}

            $('.on').removeClass('on');

            $('.banner .focus  a').eq(iNum).addClass('on');

            $('.banner .show ul li').fadeOut().eq(iNum).fadeIn(1000);       

            };

        var timer = setInterval(autoPlay,speed);

        $('.banner .show,.banner .focus').hover(function(){clearInterval(timer);},function(){timer = setInterval(autoPlay,speed);});

    });
/*轮播*/




/*轮播*/
$(function(){

        $('.hotShow .show ul li:eq(0)').show();

        var speed = 5000,iNum = 0;



        $('.hotShow .focus a').bind('click',function(){

                var aIndex = $('.hotShow .focus a').index(this);

                $('.on1').removeClass('on1');

                $(this).addClass('on1');

                $('.hotShow .show ul li').fadeOut().eq(aIndex).fadeIn();

                iNum = aIndex;

                })

        var autoPlay = function(){

            iNum++;

            if(iNum == $('.hotShow .show ul li').length){iNum=0;}

            $('.on1').removeClass('on1');

            $('.hotShow .focus  a').eq(iNum).addClass('on1');

            $('.hotShow .show ul li').fadeOut().eq(iNum).fadeIn(1000);       

            };

        var timer = setInterval(autoPlay,speed);

        $('.hotShow .show,.hotShow .focus').hover(function(){clearInterval(timer);},function(){timer = setInterval(autoPlay,speed);});

    });
/*轮播*/
