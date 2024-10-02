import formService from "./form.js";

const D = 100;

export default {
  ctx: null,
  getR() {
    return formService.getR();
  },
  init(submitFn) {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");
    this.ctx = ctx;

    ctx.translate(2 * D, 2 * D);

    function drawShape() {
      ctx.fillStyle = "#1E90FF";
      // 1. rect
      ctx.beginPath();
      ctx.rect(-D, 0, D, D / 2);
      ctx.fill();
      ctx.closePath();

      // 2. triangle
      ctx.moveTo(0, 0);
      ctx.beginPath();
      ctx.lineTo(0, -D);
      ctx.lineTo(D / 2, 0);
      ctx.lineTo(0, 0);
      ctx.closePath();
      ctx.fill();

      // 3. circle
      ctx.beginPath();
      ctx.arc(0, 0, D / 2, 0, Math.PI, false);
      ctx.closePath();
      ctx.fill();

      // center
      ctx.fillStyle = "#FF0000";
      ctx.fillRect(0, 0, 5, 5);

      // graph outline
      ctx.beginPath();
      ctx.moveTo(-2 * D, 0);
      ctx.lineTo(2 * D, 0);
      ctx.stroke();

      ctx.beginPath();
      ctx.moveTo(0, -2 * D);
      ctx.lineTo(0, 2 * D);
      ctx.stroke();
    }

    canvas.addEventListener("click", (event) => {
      const rect = canvas.getBoundingClientRect();
      const x = event.clientX - rect.left - 2 * D;
      const y = -(event.clientY - rect.top - 2 * D);

      const r = this.getR();
      if (r == null || r == undefined) return;
      const scaledX = (x / D) * r;
      const scaledY = (y / D) * r;

      console.log(`submitting coords (${scaledX}, ${scaledY})`);

      submitFn(scaledX, scaledY, r);
    });

    drawShape();
  },
  addPoint(x, y, r, color) {
    const ctx = this.ctx;
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.arc((x * D) / r, (-y * D) / r, 2, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.fill();
  },
};
