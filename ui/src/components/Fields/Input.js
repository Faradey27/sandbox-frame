import { PropTypes } from 'react';
import { Input as RawInput } from './rawFields';

const Input = ({ input, label, type, meta: { touched, error } }) => (
  <RawInput
    {...input}
    error={error}
    label={label}
    touched={touched}
    type={type}
  />
);

Input.propTypes = {
  input: PropTypes.object,
  label: PropTypes.string,
  meta: PropTypes.object,
  type: PropTypes.string,
};

export default Input;
