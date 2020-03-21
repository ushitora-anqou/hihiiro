import chisel3._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class RuleModuleSpec extends ChiselFlatSpec {
  assert(Driver(() => new RuleModule) {
    c => new PeekPokeTester(c) {
      expect(c.io.romAddr(0), 0x00.U)
      expect(c.io.romAddr(1), 0x00.U)
      expect(c.io.romAddr(2), 0x00.U)
      expect(c.io.romAddr(3), 0x00.U)
      expect(c.io.romAddr(4), 0x00.U)
      expect(c.io.romAddr(5), 0x00.U)
      expect(c.io.romAddr(6), 0x00.U)
      expect(c.io.romAddr(7), 0x00.U)
      poke(c.io.in, 0x61.U) // 'a'
      poke(c.io.romRdata(0), 0x00000001.U)  // state 0
      poke(c.io.romRdata(1), 0x00000100.U)  // state 0
      poke(c.io.romRdata(2), 0x00000100.U)  // state 0
      poke(c.io.romRdata(3), 0x00000001.U)  // state 0
      poke(c.io.romRdata(4), 0x00000001.U)  // state 0
      poke(c.io.romRdata(5), 0x00000001.U)  // state 0
      poke(c.io.romRdata(6), 0x00000201.U)  // state 0
      poke(c.io.romRdata(7), 0x00000100.U)  // state 0
      step(1)
      expect(c.io.out, 0x0000.U)

      expect(c.io.romAddr(0), 0x01.U)
      expect(c.io.romAddr(1), 0x01.U)
      expect(c.io.romAddr(2), 0x01.U)
      expect(c.io.romAddr(3), 0x01.U)
      expect(c.io.romAddr(4), 0x01.U)
      expect(c.io.romAddr(5), 0x01.U)
      expect(c.io.romAddr(6), 0x01.U)
      expect(c.io.romAddr(7), 0x01.U)
      poke(c.io.in, 0x62.U) // 'b'
      poke(c.io.romRdata(0), 0x00000002.U)  // state 1
      poke(c.io.romRdata(1), 0x00000200.U)  // state 1
      poke(c.io.romRdata(2), 0x00000200.U)  // state 1
      poke(c.io.romRdata(3), 0x00000002.U)  // state 1
      poke(c.io.romRdata(4), 0x00000002.U)  // state 1
      poke(c.io.romRdata(5), 0x00000302.U)  // state 1
      poke(c.io.romRdata(6), 0x00000301.U)  // state 1
      poke(c.io.romRdata(7), 0x00000102.U)  // state 1
      step(1)
      expect(c.io.out, 0x0000.U)

      expect(c.io.romAddr(0), 0x02.U)
      expect(c.io.romAddr(1), 0x02.U)
      expect(c.io.romAddr(2), 0x02.U)
      expect(c.io.romAddr(3), 0x02.U)
      expect(c.io.romAddr(4), 0x02.U)
      expect(c.io.romAddr(5), 0x02.U)
      expect(c.io.romAddr(6), 0x03.U)
      expect(c.io.romAddr(7), 0x02.U)
      poke(c.io.in, 0x63.U) // 'c'
      poke(c.io.romRdata(0), 0x00030002.U)  // state 2
      poke(c.io.romRdata(1), 0x00030200.U)  // state 2
      poke(c.io.romRdata(2), 0x00030200.U)  // state 2
      poke(c.io.romRdata(3), 0x00030002.U)  // state 2
      poke(c.io.romRdata(4), 0x00030002.U)  // state 2
      poke(c.io.romRdata(5), 0x00010302.U)  // state 2
      poke(c.io.romRdata(6), 0x00010204.U)  // state 3
      poke(c.io.romRdata(7), 0x00030100.U)  // state 2
      step(1)
      expect(c.io.out, 0x0001.U)  // Found 'ab'

      expect(c.io.romAddr(0), 0x02.U)
      expect(c.io.romAddr(1), 0x02.U)
      expect(c.io.romAddr(2), 0x02.U)
      expect(c.io.romAddr(3), 0x02.U)
      expect(c.io.romAddr(4), 0x02.U)
      expect(c.io.romAddr(5), 0x02.U)
      expect(c.io.romAddr(6), 0x02.U)
      expect(c.io.romAddr(7), 0x01.U)
      poke(c.io.in, 0x64.U) // 'c'
      poke(c.io.romRdata(0), 0x00030002.U)  // state 2
      poke(c.io.romRdata(1), 0x00030200.U)  // state 2
      poke(c.io.romRdata(2), 0x00030200.U)  // state 2
      poke(c.io.romRdata(3), 0x00030002.U)  // state 2
      poke(c.io.romRdata(4), 0x00030002.U)  // state 2
      poke(c.io.romRdata(5), 0x00010302.U)  // state 2
      poke(c.io.romRdata(6), 0x00000204.U)  // state 2
      poke(c.io.romRdata(7), 0x00000102.U)  // state 1
      step(1)
      expect(c.io.out, 0x0000.U)

      expect(c.io.romAddr(0), 0x02.U)
      expect(c.io.romAddr(1), 0x02.U)
      expect(c.io.romAddr(2), 0x02.U)
      expect(c.io.romAddr(3), 0x02.U)
      expect(c.io.romAddr(4), 0x02.U)
      expect(c.io.romAddr(5), 0x03.U)
      expect(c.io.romAddr(6), 0x04.U)
      expect(c.io.romAddr(7), 0x02.U)
      poke(c.io.in, 0xff.U) // 'EOF'
      poke(c.io.romRdata(0), 0x00030002.U)  // state 2
      poke(c.io.romRdata(1), 0x00030200.U)  // state 2
      poke(c.io.romRdata(2), 0x00030200.U)  // state 2
      poke(c.io.romRdata(3), 0x00030002.U)  // state 2
      poke(c.io.romRdata(4), 0x00030002.U)  // state 2
      poke(c.io.romRdata(5), 0x00020001.U)  // state 3
      poke(c.io.romRdata(6), 0x00020301.U)  // state 4
      poke(c.io.romRdata(7), 0x00030100.U)  // state 2
      step(1)
      expect(c.io.out, 0x0002.U)  // Found 'cd'
    }
  })
}
