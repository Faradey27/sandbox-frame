import { Component } from 'react';
import PureRender from 'pure-render-decorator';

const styles = {
  base: {},
};

@PureRender
class Input extends Component {
  render() {
    return (
      <div style={styles.base} />
    );
  }
}

export default Input;
