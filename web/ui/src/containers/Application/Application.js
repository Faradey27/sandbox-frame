import { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import radium from 'radium';
import pureRender from 'pure-render-decorator';

import { loadApp } from './../../actions/apps';
import { getApp } from './../../selectors/apps';

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
class Application extends Component {
  static propTypes = {
    loadApp: PropTypes.func,
    routeParams: PropTypes.object,
  }

  componentWillMount() {
    this.props.loadApp({ name: this.props.routeParams.name });
  }

  render() {
    return (
      <div style={[styles.base]}>
        {'html detailed'}
      </div>
    );
  }
}

const mapStateToProps = (state, props) => ({
  app: getApp(state, props),
});

export default connect(mapStateToProps, {
  loadApp,
})(Application);
