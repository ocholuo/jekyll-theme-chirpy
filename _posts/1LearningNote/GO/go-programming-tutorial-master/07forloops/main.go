package main

import (
	"fmt"
)

func main() {

	nums := []int{1, 2, 3}
	for x := range nums {
		fmt.Printf("print line %v\n", x)
	}

	for x := 1; x < 4; x++ {
		fmt.Printf("print a line %v\n", x)
	}
}
