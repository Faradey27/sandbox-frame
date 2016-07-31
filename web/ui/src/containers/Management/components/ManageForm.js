import React, { Component, PropTypes } from 'react';
import Radium from 'radium';
import PureRender from 'pure-render-decorator';
import { reduxForm, Field } from 'redux-form/immutable';

import { Input, RaisedButton } from '../../../components/Fields';

const styles = {
  base: {},
};

@Radium
@PureRender
class ManageForm extends Component {
  static propTypes = {
    handleSubmit: PropTypes.func, // from redux-form
    updateManagement: PropTypes.func,
  };

  static contextTypes = {
    i18n: PropTypes.object,
  };

  constructor(props) {
    super(props);
    this.submit = this.submit.bind(this);
  }

  submit(form) {
    console.log(form.toJS(), this.props.updateManagement);
  }

  render() {
    const { handleSubmit } = this.props;
    const { l } = this.context.i18n;

    return (
      <div style={[styles.base]}>
        <form onSubmit={handleSubmit(this.submit)}>
          <Field
            component={(props) =>
              <Input
                {...props}
                style={styles.input}
              />
            }
            name="something"
            placeholder={l('Something')}
            style={styles.field}
          />
          <RaisedButton
            label={l('Some')}
            type="submit"
          />
        </form>
      </div>
    );
  }
}

export default reduxForm({
  form: 'ManageForm',
})(ManageForm);
