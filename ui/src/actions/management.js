import { CALL_API } from 'redux-api-middleware';
import { API_URL } from './../constants/Api';

export const UPDATE_MANAGEMENT_START = 'UPDATE_MANAGEMENT_START';
export const UPDATE_MANAGEMENT_SUCCESS = 'UPDATE_MANAGEMENT_SUCCESS';
export const UPDATE_MANAGEMENT_FAILURE = 'UPDATE_MANAGEMENT_FAILURE';

const fetchUpdateManagement = (form) => ({
  [CALL_API]: {
    types: [
      UPDATE_MANAGEMENT_START,
      UPDATE_MANAGEMENT_SUCCESS,
      UPDATE_MANAGEMENT_FAILURE,
    ],
    method: 'POST',
    body: JSON.strinfigy(form),
    endpoint: `${API_URL}/app/${name}`,
  },
});

export const updateManagement = (form) => (dispatch) => dispatch(fetchUpdateManagement(form));
