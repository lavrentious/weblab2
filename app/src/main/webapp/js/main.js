import formService from "./form.js";
import graph from "./graph.js";

function onInit() {
  let recordHistory = [];
  const historyJSONNode = document.getElementById("recordsHistroyJSON");
  if (historyJSONNode) {
    recordHistory = JSON.parse(historyJSONNode.innerHTML);
    historyJSONNode.remove();
  }
  console.log("history", recordHistory);

  formService.init(() => {});

  graph.init((x, y, r) => {
    console.log(`received coords (${x}, ${y}) with radius ${r}`);
    graph.addPoint(x, y, r, "red");
    window.location.replace(`./controller?x=${x}&y=${y}&r=${r}`);
  });
  for (const record of recordHistory) {
    graph.addPoint(record.x, record.y, record.r, "#151d3a");
  }

}

onInit();
