import { Component, PropTypes } from 'react';
import TextField from 'material-ui/TextField';

const styles = {
  base: {},
};

class Input extends Component {
  static propTypes = {
    input: PropTypes.object,
    style: PropTypes.object,
  }

  render() {
    return (
      <div style={styles.base}>
        <TextField
          {...this.props.input}
          style={this.props.style}
        />
      </div>
    );
  }
}

export default Input;
