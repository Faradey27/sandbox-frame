import { Component, PropTypes } from 'react';
import RaisedButton from 'material-ui/RaisedButton';

const styles = {
  base: {},
};

class RaisedButtonWrap extends Component {
  static propTypes = {
    action: PropTypes.func,
    label: PropTypes.string,
    style: PropTypes.object,
    type: PropTypes.string,
  }

  render() {
    return (
      <div style={styles.base}>
        <RaisedButton
          label={this.props.label}
          secondary
          style={this.props.style}
          type={this.props.type}
        />
      </div>
    );
  }
}

export default RaisedButtonWrap;
