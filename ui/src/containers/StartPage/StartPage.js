import { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import Immutable from 'immutable';
import radium from 'radium';
import pureRender from 'pure-render-decorator';

import { getFilteredApps } from './../../selectors/apps';
import { loadApps } from './../../actions/apps';

import AppCard from './../../components/AppCard';

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
class StartPage extends Component {
  static propTypes = {
    apps: PropTypes.instanceOf(Immutable.List),
    loadApps: PropTypes.func.isRequired,
  }

  componentWillMount() {
    this.props.loadApps();
  }

  renderApps() {
    return this.props.apps.map((app) => (
      <AppCard
        app={app}
        key={app.get('name')}
      />
    ));
  }

  render() {
    return (
      <div style={[styles.base]}>
        {this.renderApps()}
      </div>
    );
  }
}

const mapStateToProps = (state, props) => ({
  apps: getFilteredApps(state, props),
});

export default connect(mapStateToProps, {
  loadApps,
})(StartPage);
