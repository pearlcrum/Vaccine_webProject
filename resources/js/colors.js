          var Body={
            Color:function (color)
            {
              //document.querySelector('body').style.color=color;
              $('body').css('color',color);
            },
            BackgroundColor:function(color)
            {
              //document.querySelector('body').style.backgroundColor=color;
              $('body').css('backgroundColor',color);
            }
          }
          var Links={
            Color:function(color)
            {

                var alist=document.querySelectorAll('a');
                var i=0;
                // while(i<alist.length)
                // {
                //   if(i===0)
                //   {
                //     i+=1;
                //   }
                //   else
                //   {
                //     alist[i].style.color=color;
                //     i=i+1;
                //   }
                // }
                $('a').css('color',color);
            }

          }
          var Size={
            setSize: function(size){
                $('h1').css('font-size',size);
            }
          }



          function nightDayHandeler(self)
          {
            var target = document.querySelector('body');

            if(self.value==='night')
            {
              Body.BackgroundColor('black');
              Body.Color('white');
              Size.setSize("50px");
              self.value ='day';
              Links.Color('powderblue')
            }
            else
            {
              Body.BackgroundColor('white');
              Body.Color('black');
              Size.setSize("50px");
              self.value ='night';
              Links.Color('blue');
            }
        }
