import { Component, PropTypes } from 'react';
import FlatButton from 'material-ui/FlatButton';

const styles = {
  base: {},
};

class FlatButtonWrap extends Component {
  static propTypes = {
    action: PropTypes.func,
    containerElement: PropTypes.node,
    label: PropTypes.string,
    style: PropTypes.object,
  }

  render() {
    return (
      <div style={styles.base}>
        <FlatButton
          containerElement={this.props.containerElement}
          label={this.props.label}
          style={this.props.style}
        />
      </div>
    );
  }
}

export default FlatButtonWrap;
