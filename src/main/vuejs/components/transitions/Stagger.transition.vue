<script>
import velocity from 'velocity-animate';
export default {
  functional: true,
  name: 'stagger-transition',
  props: {
    delay: {
      type: Number,
      default () {
        return 150;
      }
    },
    tag: {
      type: String,
      default () {
        return 'div';
      }
    },
    slide: {
      type: Boolean,
      default () {
        return true;
      }
    }
  },
  render (h, context) {
    var excluded = []
    const options = {
      props: {
        tag: context.props.tag,
        css: false,
        appear: true
      },
      on: {
        beforeEnter: (el) => {
          if (context.props.slide) {
            velocity.hook(el, "translateX", "-50px");
            velocity.hook(el, "skewX", "45deg");
            // velocity.hook(el, 'skewY', '5deg');
          }

          el.style.opacity = 0;

          [ ...el.children ].filter(e => {
            return [ ...e.attributes ].some(a => a.name === 'stagger-exclude')
          }).forEach(e =>  {
            e.style.opacity = 0
            excluded.push(e)
          })
        },
        enter: (el, done) => {
          const children = Array.from(el.parentElement.children);
          const index = children.findIndex(e => e === el);

          const delay = context.props.delay;

          el.parentElement.style.overflow = "hidden"
          velocity(el, {
            opacity: 1,
            translateX: '0px',
            skewX: '0deg',
            skewY: '0deg'
          }, {
            queue: false,
            duration:  delay * 3.1,
            delay: index * delay,
            easing: 'ease',
            complete: () => {
              excluded.forEach(el => {
                velocity(el, { opacity: 1 }, { delay: delay * 2 , duration: 200 })
              })

              if (done) {
                done();
              }
            },
          });
        },
        leave: (el, done) => {
          const children = Array.from(el.parentElement.children);

          const index = children.findIndex(e => e === el);
          const order = (children.length - 1) - index;

          const delay = context.props.delay;

          velocity(el, {
            opacity: 0,
            translateX: '100px'
          }, {
            queue: false,
            duration: delay * 3.1,
            delay: order * delay,
            easing: 'ease',
            complete: done,
          });
        }
      }
    };

    return h('transition-group', options, context.children);
  }
};
</script>