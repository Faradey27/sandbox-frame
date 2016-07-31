/* eslint-disable import/no-deprecated */
/* eslint-disable import/imports-first */

import { render } from 'react-dom';
import { browserHistory } from 'react-router';
import injectTapEventPlugin from 'react-tap-event-plugin';
import { syncHistoryWithStore } from 'react-router-redux';
import Root from './containers/Root';
import configureStore from './store/configureStore';

import 'nvd3/build/nv.d3.min.css';
import 'font-awesome/css/font-awesome.min.css';
import './assets/normalize.less';
import './assets/media.less';
import './assets/typography.less';
import './styles.less';

const store = configureStore();
const history = syncHistoryWithStore(browserHistory, store, {
  selectLocationState(state) {
    return state.get('routing').toJS();
  },
});

/*
  we require next line to support onTouchTap event
  which will solve a lot of "click" problems for mobile devices
*/
injectTapEventPlugin();

render(
  <Root
    history={history}
    store={store}
  />,
  document.getElementById('content')
);
