import chisel3._

class StateMachineTile extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(1.W))
    val romRdata = Input(UInt(32.W))
    val romAddr = Output(UInt(8.W))
    val out = Output(UInt(16.W))
  })

  val curState = RegInit(0.U(8.W))

  io.romAddr := curState
  io.out := io.romRdata(31, 16)
  when (io.in === 0.U) {
    curState := io.romRdata(7, 0)
  }.otherwise {
    curState := io.romRdata(15, 8)
  }
}
