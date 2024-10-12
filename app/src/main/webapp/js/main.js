import form from "./form.js";
import graph from "./graph.js";
import history from "./history.js";

function onInit() {
  form.init(() => {});

  graph.init((x, y, r) => {
    console.log(`received coords (${x}, ${y}) with radius ${r}`);
    graph.addPoint(x, y, r, "red");
    window.location.replace(`./controller?x=${x}&y=${y}&r=${r}`);
  });

  history.init(graph);
}

onInit();
