# makes header parameters more readable for completion calls
def url_deformatter(src):
    return src.replace("+"," ")

def url_formatter(src):
    return src.replace(" ","+")

def remove_backslashes(src):
    formatted_json = ""
    for character in src:
        if character != "\\":
            formatted_json += character
    return formatted_json