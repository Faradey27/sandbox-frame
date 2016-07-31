import { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import radium from 'radium';
import pureRender from 'pure-render-decorator';

import { updateManagement } from './../../actions/management';

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
    updateManagement: PropTypes.func.isRequired,
  }

  render() {
    return (
      <div style={[styles.base]}>
        <ManageForm updateManagement={this.props.updateManagement} />
      </div>
    );
  }
}

export default connect(null, {
  updateManagement,
})(Managment);
