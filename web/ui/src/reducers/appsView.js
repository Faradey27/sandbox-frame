import { createReducer } from 'redux-immutablejs';
import Immutable from 'immutable';

import * as ActionTypes from './../actions/apps';

const initialState = Immutable.fromJS({
  isFetching: false,
  error: null,
  app: {},
});

export default createReducer(initialState, {
  [ActionTypes.LOAD_APPS_START]: (state) => state.merge({
    isFetching: true,
    error: null,
  }),
  [ActionTypes.LOAD_APPS_SUCCESS]: (state, action) => state.merge({
    isFetching: false,
    error: null,
    app: action.payload.enitities.result,
  }),
  [ActionTypes.LOAD_APPS_FAILURE]: (state) => state.merge({
    isFetching: false,
    error: true,
  }),
  [ActionTypes.LOAD_APP_START]: (state, action) => state.merge({
    isFetching: true,
    error: null,
    app: state.get('app').set(action.meta.name, new Immutable.Map({
      isFetching: true,
      error: null,
    })),
  }),
  [ActionTypes.LOAD_APP_SUCCESS]: (state, action) => state.merge({
    isFetching: false,
    error: null,
    app: state.get('app').set(action.meta.name, new Immutable.Map({
      isFetching: false,
      error: null,
    })),
  }),
  [ActionTypes.LOAD_APP_FAILURE]: (state, action) => state.merge({
    isFetching: false,
    error: true,
    app: state.get('app').set(action.meta.name, new Immutable.Map({
      isFetching: false,
      error: true,
    })),
  }),
  [ActionTypes.FILTER_APPS]: (state, action) => state.merge({
    isFetching: false,
    error: true,
    app: state.get('app').filter((app) => app.get('name') === action.filter),
  }),
});
