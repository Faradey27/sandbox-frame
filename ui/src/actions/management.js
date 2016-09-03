import { CALL_API } from 'redux-api-middleware';
import { API_URL } from './../constants/Api';

export const UPDATE_MANAGEMENT_START = 'UPDATE_MANAGEMENT_START';
export const UPDATE_MANAGEMENT_SUCCESS = 'UPDATE_MANAGEMENT_SUCCESS';
export const UPDATE_MANAGEMENT_FAILURE = 'UPDATE_MANAGEMENT_FAILURE';

const fetchUpdateManagementConfig = (form) => ({
  [CALL_API]: {
    types: [
      UPDATE_MANAGEMENT_START,
      UPDATE_MANAGEMENT_SUCCESS,
      UPDATE_MANAGEMENT_FAILURE,
    ],
    method: 'POST',
    body: JSON.strinfigy(form && form.toJS()),
    endpoint: `${API_URL}/app/${name}`,
  },
});

export const updateManagementConfig = (form) => (dispatch) => dispatch(fetchUpdateManagementConfig(form));
