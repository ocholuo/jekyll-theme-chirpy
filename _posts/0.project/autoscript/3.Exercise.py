
# 3. Exercise: Creating Files Based on User Input
#
# Write a script that prompts the user for:
# - A file_name where it should write the content.
# - The content that should go in the file. The script should keep accepting lines of text until the user enters an empty line.
# - After the user enters an empty line, write all of the lines to the file and end the script.

def create_input_file():
    file_name = input("Please enter a file name: ")
    print("tape in your word:")

    eof = False
    lines = []
    with open (file_name, 'w') as f:
        while not eof:
            line = input()
            if line.strip():
                lines.append(f"{line}\n")
            else:
                eof = True
        f.writelines(lines)
        f.close()
        print(f"Lines written to file: {file_name}")

    with open (file_name, 'r') as f:
        words=f.read()
        print(words)

create_input_file()

