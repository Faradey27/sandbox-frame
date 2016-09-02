import { CALL_API } from 'redux-api-middleware';
import { API_URL } from './../constants/Api';

export const LOAD_APPS_START = 'LOAD_APPS_START';
export const LOAD_APPS_SUCCESS = 'LOAD_APPS_SUCCESS';
export const LOAD_APPS_FAILURE = 'LOAD_APPS_FAILURE';

const fetchApps = () => ({
  [CALL_API]: {
    types: [
      LOAD_APPS_START,
      LOAD_APPS_SUCCESS,
      LOAD_APPS_FAILURE,
    ],
    method: 'GET',
    endpoint: `${API_URL}/apps`,
  },
});

export const loadApps = () => (dispatch) => dispatch(fetchApps());

export const LOAD_APP_START = 'LOAD_APP_START';
export const LOAD_APP_SUCCESS = 'LOAD_APP_SUCCESS';
export const LOAD_APP_FAILURE = 'LOAD_APP_FAILURE';

const fetchApp = ({ name }) => ({
  [CALL_API]: {
    types: [
      { type: LOAD_APP_START, meta: { name } },
      { type: LOAD_APP_SUCCESS, meta: { name } },
      { type: LOAD_APP_FAILURE, meta: { name } },
    ],
    method: 'GET',
    endpoint: `${API_URL}/app/${name}`,
  },
});

export const loadApp = ({ name }) => (dispatch) => dispatch(fetchApp({ name }));

const FILTER_APPS = 'FILTER_APPS';

export const filterApps = (filter) => ({ type: FILTER_APPS, filter });
