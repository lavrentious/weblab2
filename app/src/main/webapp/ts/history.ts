import Graph from "./graph.js";

export default {
  init(graph: typeof Graph) {
    fetch("./history", {
      headers: {
        "If-Modified-Since":
          localStorage.getItem("lastHistoryFetchDate") ??
          new Date().toUTCString(),
      },
    })
      .then((res) => {
        if (res.status === 304) {
          console.log("using cached history");
          return JSON.parse(localStorage.getItem("history") ?? "[]");
        }
        return res.json();
      })
      .then((history) => {
        console.log("history", history);
        localStorage.setItem("history", JSON.stringify(history));
        localStorage.setItem("lastHistoryFetchDate", new Date().toUTCString());
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
