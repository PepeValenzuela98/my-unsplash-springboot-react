export default function (values) {
  let errors = {};

  if (!values.name) {
    errors.name = "Name is obligatory";
  }

  if (!values.email) {
    errors.email = "Email is obligatory";
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)) {
    errors.email = "Email not valid";
  }

  if (!values.password) {
    errors.password = "Password is obligatory";
  } else if (values.password.length < 6) {
    errors.password = "The password must be at least 6 characters";
  }

  if (values.passwordRepeat !== values.password) {
    errors.passwordRepeat = "Passwords do not match";
  }

  return errors;
}
