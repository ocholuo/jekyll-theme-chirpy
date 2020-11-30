package main

import (
	"fmt"
)

func main() {
	var input string
	for {
		fmt.Println("Enter your name:")
		fmt.Scanln(&input)
		if input == "stop" {
			break
		} else {
			fmt.Printf("You Entered: %v\n", input)
		}
	}
}
