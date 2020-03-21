import chisel3._

class RuleModule extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(8.W))
    val romRdata = Input(Vec(8, UInt(32.W)))
    val romAddr = Output(Vec(8, UInt(8.W)))
    val out = Output(UInt(16.W))
  })

  val smt = Array(
    Module(new StateMachineTile), // [0]
    Module(new StateMachineTile), // [1]
    Module(new StateMachineTile), // [2]
    Module(new StateMachineTile), // [3]
    Module(new StateMachineTile), // [4]
    Module(new StateMachineTile), // [5]
    Module(new StateMachineTile), // [6]
    Module(new StateMachineTile)  // [7]
  )

  for (i <- 0 until 8) {
    smt(7 - i).io.in := io.in(i)
    smt(i).io.romRdata := io.romRdata(i)
    io.romAddr(i) := smt(i).io.romAddr
  }

  io.out := ((smt(0).io.out & smt(1).io.out) &
             (smt(2).io.out & smt(3).io.out)) &
            ((smt(4).io.out & smt(5).io.out) &
             (smt(6).io.out & smt(7).io.out))
}
