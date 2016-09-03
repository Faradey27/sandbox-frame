import { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import radium from 'radium';
import pureRender from 'pure-render-decorator';

import { updateManagementConfig } from './../../actions/management';

import ManageForm from './components/ManageForm';

const styles = {
  base: {
    display: 'flex',
    justifyContent: 'center',
    flexWrap: 'wrap',
    padding: 20,
  },
};

@radium
@pureRender
class Managment extends Component {
  static propTypes = {
    updateManagementConfig: PropTypes.func,
  }

  render() {
    return (
      <div style={[styles.base]}>
        <ManageForm updateManagementConfig={this.props.updateManagementConfig} />
      </div>
    );
  }
}

export default connect(null, {
  updateManagementConfig,
})(Managment);
