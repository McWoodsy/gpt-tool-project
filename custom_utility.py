# makes header parameters more readable for completion calls
def url_deformatter(src):
    return src.replace("+"," ")

def url_formatter(src):
    return src.replace(" ","+")