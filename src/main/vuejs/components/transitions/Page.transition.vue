<script>
const scaleFactor = 0.98;
const easing = [500, 20];

import velocity from 'velocity-animate';
export default {
  functional: true,
  name: 'page-transition',
  props: {
    duration: {
      type: Number,
      default() {
        return 750;
      }
    },
    tag: {
      type: String,
      default() {
        return 'div';
      }
    }
  },
  render(h, context) {
    const options = {
      props: {
        tag: context.props.tag,
        css: false,
        appear: true,
        mode: 'out-in'
      },
      on: {
        beforeEnter: el => {
          [...el.children]
            .filter(
              element =>
                !element.classList.contains('loader') &&
                !element.classList.contains('modal')
            )
            .forEach(element => {
              velocity.hook(element, 'scaleX', scaleFactor);
              velocity.hook(element, 'scaleY', scaleFactor);
              velocity.hook(element, 'rotateY', '40deg');
              element.style.opacity = 0;
            });
        },
        enter: (el, done) => {
          const promises = [...el.children]
            .filter(
              element =>
                !element.classList.contains('loader') &&
                !element.classList.contains('modal')
            )
            .map((element, index) => {
              return velocity(
                element,
                {
                  opacity: 1,
                  scaleX: 1,
                  scaleY: 1,
                  rotateY: 0
                },
                {
                  duration: context.props.duration,
                  delay: index * (context.props.duration * 0.23),
                  easing
                }
              );
            });

          Promise.all(promises).then(() => {
            if (done) {
              done();
            }
          });
        },
        afterEnter: (el, done) => {
          [...el.children]
            .filter(
              element =>
                !element.classList.contains('loader') &&
                !element.classList.contains('modal')
            )
            .forEach(element => {
              element.removeAttribute('style');
            });
        },
        leave: (el, done) => {
          velocity(
            el,
            {
              opacity: 0,
              scaleX: scaleFactor,
              scaleY: scaleFactor
            },
            context.props.duration,
            easing
          ).then(() => {
            if (done) {
              done();
            }
          });
        }
      }
    };

    return h('transition', options, context.children);
  }
};
</script>