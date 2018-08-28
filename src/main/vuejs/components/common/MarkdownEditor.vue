<script>
import { MdToolbarMap } from '@/maps';
export default {
  props: {
    value: {
      type: String,
      default () {
        return '';
      }
    }
  },
  computed: {
    editor () {
      return this.$el.getElementsByTagName('textarea')[0];
    }
  },
  render (h) {
    return h('div', {
      class: 'md-editor'
    }, [
      h('div', {
        class: 'md-toolbar'
      }, MdToolbarMap.map(
        item => {
          if (item.type === 'separator') return h('span', { class: 'md-separator' });
          return h('a', {
            on: {
              click: () => {
                item.command(this.editor);
              }
            }
          }, [ h('i', { class: `fa fa-${item.icon}`}) ]);
        }
      )),
      h('textarea', {
        on: {
          input: e => {
            this.$emit('input', e.target.value);
          }
        },
        domProps: {
          value: this.value
        }
      })
    ]);
  }
};
</script>

<style lang="scss">
.md-editor {
  border: 1px solid #ccc;
  border-radius: 5px;

  .md-toolbar {
    display: flex;
    border-bottom: 1px solid #ccc;

    a {
      display: block;
      padding: 5px 15px;
      border: 1px solid transparent;;

      &:hover {
        background-color: #eee;
      }
    }

    .md-separator {
      border-right: 1px solid #ccc;
      margin: 5px 0;
    }
  }

  textarea {
    resize: none;
    height: 400px;
    width: 100%;
    border: none;
    padding: 10px;
  }
}
</style>
