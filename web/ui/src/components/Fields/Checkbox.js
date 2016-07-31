import { Component, PropTypes } from 'react';
import Checkbox from 'material-ui/Checkbox';

const styles = {
  base: {},
};

class CheckboxWrap extends Component {
  static propTypes = {
    input: PropTypes.object,
    label: PropTypes.string,
    style: PropTypes.object,
  }

  render() {
    return (
      <div style={styles.base}>
        <Checkbox
          checked={this.props.input.checked}
          label={this.props.input.label}
          style={this.props.style}
          onCheck={(e) => this.props.input.onChange(e)}
        />
      </div>
    );
  }
}

export default CheckboxWrap;
