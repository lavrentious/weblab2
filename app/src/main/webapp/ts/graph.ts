import formService from "./form";
import Toastify from "toastify-js";

const D = 100;

export default {
  ctx: null as CanvasRenderingContext2D | null,
  getR() {
    return formService.getR();
  },
  init(submitFn: (x: number, y: number, r: number) => void) {
    const canvas = document.getElementById("canvas") as HTMLCanvasElement;
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
      if (r == null || r == undefined) {
        Toastify({
          text: "не указан параметр R",
          duration: 3000,
          newWindow: true,
          close: true,
          gravity: "top", // `top` or `bottom`
          position: "center", // `left`, `center` or `right`
          stopOnFocus: true, // Prevents dismissing of toast on hover
          style: {
            background: "linear-gradient(to right, #c93d96, #b00015)",
            borderRadius: "5px",
          },
          onClick: function () {}, // Callback after click
        }).showToast();
        return;
      }
      const scaledX = (x / D) * r;
      const scaledY = (y / D) * r;

      console.log(`submitting coords (${scaledX}, ${scaledY})`);

      submitFn(scaledX, scaledY, r);
    });

    drawShape();
  },
  addPoint(x: number, y: number, r: number, color: string) {
    const ctx = this.ctx;
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.arc((x * D) / r, (-y * D) / r, 2, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.fill();
  },
};
