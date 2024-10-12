export default {
  init(graph) {
    fetch("./history")
      .then((res) => res.json())
      .then((history) => {
        console.log("history", history);
        for (const record of history) {
          graph.addPoint(record.x, record.y, record.r, "#151d3a");
        }
      });

    document
      .getElementById("historyClearButton")
      .addEventListener("click", this.clear);
  },
  clear() {
    console.log("clearing history");
    fetch("./history", {
      method: "DELETE",
    }).then(() => {
      location.reload();
    });
  },
};
