export default function (values) {
  let errors = {};

  if (values.label.trim() === "") {
    errors.label = "Label is obligatory";
  } else if (values.label.length > 101) {
    errors.label = "The label must be a minimum of 100 characters";
  }

  if (
    values.photoUrl && // eslint-disable-next-line
    !/https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/.test(
      values.photoUrl
    )
  ) {
    errors.photo = "URL not valid";
  }

  if (values.photoUrl.trim() === "" && !values.uploadPhoto) {
    errors.photo = "Put a photo url or upload one";
  }

  if (values.photoUrl.trim() !== "" && values.uploadPhoto) {
    errors.photo = "Select only one option";
  }

  return errors;
}
