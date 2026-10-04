"""Generate simple 16x16 RGBA textures for Pale Survival (pure stdlib PNG writer)."""
import os
import struct
import zlib

BASE = os.path.expanduser("~/pale-survival/src/main/resources/assets/palesurvival/textures")


def write_png(path, rows, palette):
    assert len(rows) == 16, f"{path}: need 16 rows, got {len(rows)}"
    pixels = []
    for row in rows:
        assert len(row) == 16, f"{path}: bad row {row!r}"
        pixels.append([palette[ch] for ch in row])

    raw = b"".join(
        b"\x00" + b"".join(struct.pack("4B", *px) for px in row) for row in pixels
    )

    def chunk(tag, data):
        c = tag + data
        return struct.pack(">I", len(data)) + c + struct.pack(">I", zlib.crc32(c) & 0xFFFFFFFF)

    png = (
        b"\x89PNG\r\n\x1a\n"
        + chunk(b"IHDR", struct.pack(">IIBBBBB", 16, 16, 8, 6, 0, 0, 0))
        + chunk(b"IDAT", zlib.compress(raw))
        + chunk(b"IEND", b"")
    )
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "wb") as f:
        f.write(png)
    print("wrote", path, len(png), "bytes")


# --- pale berry (item): pale berries with a leaf ---
berry_palette = {
    ".": (0, 0, 0, 0),
    "g": (90, 122, 58, 255),
    "s": (107, 74, 47, 255),
    "p": (232, 217, 201, 255),
    "P": (255, 255, 255, 255),
}
berry = [
    "................",
    ".....gg.........",
    "....gggg....pp..",
    ".....gg....pppp.",
    ".....s....ppPpp.",
    ".....s....ppPpp.",
    ".....s.....pppp.",
    "..p..s.......pp.",
    ".ppp.....pp.....",
    "ppPpp...pppp....",
    "ppPpp..ppPppp...",
    ".ppp...ppPppp...",
    "..p.....pppp....",
    "..........pp....",
    "................",
    "................",
]

# --- eyeblossom stew (item): bowl of soup with petals ---
stew_palette = {
    ".": (0, 0, 0, 0),
    "o": (232, 106, 42, 255),
    "E": (250, 220, 120, 255),
    "s": (207, 198, 184, 255),
    "b": (107, 74, 47, 255),
    "B": (70, 46, 28, 255),
}
stew = [
    "................",
    "................",
    "...oo....oo.....",
    "..oooo..oooo....",
    "..oEEo..oEEo....",
    "..oooo..oooo....",
    "...oo....oo.....",
    "....ssssss......",
    "...ssssssss.....",
    "...ssssssss.....",
    "....ssssss......",
    ".....bbbb.......",
    ".....bbbb.......",
    "......bb........",
    "................",
    "................",
]

# --- resin lantern (block, all sides): dark resin bricks, glowing core ---
lantern_palette = {
    "d": (122, 46, 18, 255),
    "m": (58, 31, 16, 255),
    "c": (255, 179, 71, 255),
    "w": (255, 240, 200, 255),
}
lantern = [
    "dddddddddddddddd",
    "dmmdmmdmmdmmdmmd",
    "dddddddddddddddd",
    "dmdddddddddddmd.",
    "ddddccccccccdddd",
    "dmmdccwcccccmmd.",
    "ddddccccccccdddd",
    "ddddccccccwwdddd",
    "ddddccwcccccddd.",
    "ddddccccccccdddd",
    "ddddccccccccdddd",
    "dmdddddddddddmdd",
    "dddddddddddddddd",
    "dmmdmmdmmdmmdmmd",
    "dddddddddddddddd",
    "dddddddddddddddd",
]
# fix rows to exactly 16 chars (strip stray dots)
lantern = [r.replace(".", "")[:16].ljust(16, "d") for r in lantern]

# --- creaking plush (block, all sides): grey bark, three orange eyes ---
plush_palette = {
    "g": (138, 138, 132, 255),
    "k": (90, 90, 86, 255),
    "e": (255, 122, 26, 255),
}
plush = [
    "gggggggggggggggg",
    "ggkggggkggggkgg.",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "gggegggegggegggg",
    "gggegggegggegggg",
    "gggegggegggegggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "ggkggggkggggkggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
    "gggggggggggggggg",
]
plush = [r.replace(".", "")[:16].ljust(16, "g") for r in plush]

write_png(f"{BASE}/item/pale_berry.png", berry, berry_palette)
write_png(f"{BASE}/item/eyeblossom_stew.png", stew, stew_palette)
write_png(f"{BASE}/block/resin_lantern.png", lantern, lantern_palette)
write_png(f"{BASE}/block/creaking_plush.png", plush, plush_palette)
print("done")
