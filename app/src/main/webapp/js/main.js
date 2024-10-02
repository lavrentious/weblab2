import formService from "./form.js";


function onInit() {
  console.log("initializing form");
  formService.init(() => { });
}

onInit();
