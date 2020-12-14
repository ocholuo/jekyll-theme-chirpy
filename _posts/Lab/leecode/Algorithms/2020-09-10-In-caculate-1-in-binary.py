
# calculate number of 1s in the binary form.

numinput = int(raw_input())
calnum= str(bin(numinput))
num=calnum.count('1')
print(num)


def NumberOf1(self, n):
    if n >= 0:
        return bin(n).count('1')
    else:
        return bin(n & 0xffffffff).count('1')