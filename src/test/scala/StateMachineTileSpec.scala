import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class StateMachineTileSpec extends ChiselFlatSpec {
  assert(Driver(() => new StateMachineTile) {
    c => new PeekPokeTester(c) {
      poke(c.io.in, 0.U)
      poke(c.io.romRdata, 0x12345678.U)
      step(1)
      expect(c.io.out, 0x1234.U)
      expect(c.io.romAddr, 0x78.U)

      poke(c.io.in, 1.U)
      poke(c.io.romRdata, 0x12345678.U)
      step(1)
      expect(c.io.out, 0x1234.U)
      expect(c.io.romAddr, 0x56.U)
    }
  })
}
