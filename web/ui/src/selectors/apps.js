import { createSelector } from 'reselect';
import Immutable from 'immutable';

// TODO in future this logic should be changed to get data by query from location
const getFilteredAppsList = (state) => state.getIn(['entities', 'apps']);

export const getFilteredApps = createSelector(
  [getFilteredAppsList],
  (apps) => apps || new Immutable.List()
);

const getAppData = (state, props) => state.getIn(['entities', 'apps', props.routeParams.name]);

export const getApp = createSelector(
  [getAppData],
  (app) => app || new Immutable.Map()
);
