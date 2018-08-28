<script>
import velocity from 'velocity-animate';

var initialHeight = 0;
var finalHeight = 0;

export default {
  functional: true,
  render (createElement, context) {
    context.data.on = {
      beforeEnter: el => {
        velocity.hook(el, 'skewY', '8deg')
      },
      enter: (el, done) => {
        // initialHeight = el.clientHeight;
        el.style.height = 'auto';
        finalHeight = el.clientHeight;
        el.style.height = `${initialHeight}px`;

        velocity(el, {
          height: finalHeight,
          skewY: '0deg',
          opacity: 1
        }, {
          duration: 450,
          easing: [500, 20],
          queue: false,
          complete: done
        });
      },
      leave: (el, done) => {
        velocity(el, {
          height: initialHeight,
          opacity: 0.5
        }, {
          duration: 500,
          easing: [200, 20],
          queue: false,
          complete: done
        });
      },
      afterLeave: el => {
        el.style.height = 'auto';
        el.style.opacity = 1;
        el.style.transform = ''
      }
    };

    context.data.css = false;
    return createElement('transition', context.data, context.children);
  }
};
</script>