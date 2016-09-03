/* eslint-disable react/jsx-handler-names */
import { PropTypes } from 'react';
import { Checkbox as RawCheckbox } from './rawFields';

const Checkbox = ({ input, label }) => (
  <RawCheckbox
    checked={Boolean(Checkbox.value)}
    label={label}
    onCheck={input.onChange}
  />
);

Checkbox.propTypes = {
  input: PropTypes.object,
  label: PropTypes.string,
};

export default Checkbox;
