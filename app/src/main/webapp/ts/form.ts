const R_VALUES = [1, 1.5, 2, 2.5, 3];

export default {
  xInput: document.getElementById("xInput") as HTMLInputElement,
  yInput: document.getElementById("yInput") as HTMLInputElement,
  formRFieldset: document.getElementById(
    "formRFieldset"
  ) as HTMLFieldSetElement,
  setSubmitActive(isActive: boolean) {
    if (isActive)
      document.getElementById("submitButton").removeAttribute("disabled");
    else document.getElementById("submitButton").setAttribute("disabled", "");
  },
  isInt(value: string) {
    return (
      value !== "" &&
      !isNaN(+value) &&
      parseInt(Number(+value).toString()) == +value &&
      !isNaN(parseInt(value, 10))
    );
  },
  isFloat(value: string) {
    return (
      value !== "" &&
      !isNaN(+value) &&
      parseFloat(Number(+value).toString()) == +value &&
      !isNaN(parseFloat(value))
    );
  },
  checkX() {
    const x = this.xInput.value;
    return this.isFloat(x) && -3 <= x && x <= 3;
  },
  checkY() {
    const y = this.yInput.value;
    return this.isFloat(y) && -5 <= y && y <= 5;
  },
  checkR() {
    const r = this.getR();
    return r != undefined && r != null;
  },
  checkAll() {
    return this.checkX() && this.checkY() && this.checkR();
  },
  validateX() {
    this.resetX();
    const isValid = this.checkX();
    this.xInput.classList.add(isValid ? "valid" : "invalid");
    this.setSubmitActive(this.checkAll());
    return isValid;
  },
  validateY() {
    this.resetY();
    const isValid = this.checkY();
    this.yInput.classList.add(isValid ? "valid" : "invalid");
    this.setSubmitActive(this.checkAll());
    return isValid;
  },
  validateR() {
    this.resetR();
    const isValid = this.checkR();
    this.formRFieldset.classList.add(isValid ? "valid" : "invalid");
    this.setSubmitActive(this.checkAll());
    return isValid;
  },
  getR() {
    return (
      (document.querySelector('input[name="r"]:checked') as HTMLInputElement)
        ?.value || null
    );
  },
  resetX() {
    this.xInput.classList.remove("valid");
    this.xInput.classList.remove("invalid");
  },
  resetY() {
    this.yInput.classList.remove("valid");
    this.yInput.classList.remove("invalid");
  },
  resetR() {
    this.formRFieldset.classList.remove("valid");
    this.formRFieldset.classList.remove("invalid");
  },
  validate() {
    return +this.validateX() + this.validateY() + this.validateR() === 3;
  },
  getFormData() {
    if (this.validate())
      return {
        x: this.xInput.value,
        y: this.yInput.value,
        r: this.getR(),
      };
  },
  init(onSubmit?: () => void) {
    R_VALUES.forEach((value, i) => {
      const label = document.createElement("label");
      const input = document.createElement("input");
      input.type = "radio";
      input.name = "r";
      input.value = value.toString();

      label.appendChild(input);
      label.appendChild(document.createTextNode(value.toString()));
      this.formRFieldset.appendChild(label);
    });

    this.xInput.addEventListener("focus", () => {
      this.resetX();
    });
    this.xInput.addEventListener("blur", () => {
      this.validateX();
    });
    this.yInput.addEventListener("focus", () => {
      this.resetY();
    });
    this.yInput.addEventListener("blur", () => {
      this.validateY();
    });
    this.formRFieldset.addEventListener("change", () => {
      this.validateR();
    });
  },
  resetForm() {
    this.xInput.value = "";
    this.yInput.value = "";
    document
      .querySelectorAll('input[name="r"]:checked')
      .forEach((input: HTMLInputElement) => (input.checked = false));
    this.resetX();
    this.resetY();
    this.resetR();
  },
};
