const schema = joi.object({
  email: joi
    .string()
    .email({ tlds: { allow: false } })
    .label("email")
    .required(),
  password: joi.string().alphanum().min(3).label("password").required(),
});

function validate(dataObject) {
  // dataObject = {username:"",email:""}
  const result = schema.validate(
    {
      ...dataObject,
    },
    { abortEarly: false }
  );
  return result;
}

// contact form

$(document).ready(function () {
  $(".contact-form").on("submit", function (e) {
    // prevent form submission
    e.preventDefault();
    const contactForm = this;

    const emailField = $(contactForm).find("#email");

    const passwordField = $(contactForm).find("#password");

    // bootstrap alert message
    const responseMessage = $(this).find("#responseMessage");

    const formErrors = validate({
      email: emailField.val(),
      password: passwordField.val(),
    });

    const initialErros = {
      email: null,
      password: null,
    };

    if (formErrors?.error) {
      const { details } = formErrors.error;
      details.map((detail) => {
        initialErros[detail.context.key] = detail.message;
      });
    }

    // write the errors to the UI
    Object.keys(initialErros).map((errorName) => {
      if (initialErros[errorName] !== null) {
        // if the error exist
        // username input #username
        $(`#${errorName}`).removeClass("is-valid").addClass("is-invalid");

        // invalid feedback element
        $(`#${errorName}`)
          .next(".invalid-feedback")
          .text(initialErros[errorName]);
      } else {
        $(`#${errorName}`).removeClass("is-invalid").addClass("is-valid");
      }
    });

    // to submit
    let isFormValid = Object.values(initialErros).every(
      (value) => value === null
    );
    if (isFormValid) {
      contactForm.reset();
      $(responseMessage).addClass("show");
      $(contactForm)
        .find(".is-valid, .is-invalid")
        .removeClass("is-valid is-invalid");
    } else alert("error");
  });
});
