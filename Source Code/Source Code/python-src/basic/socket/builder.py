"""
A class for encoding and decoding messages.

Summary:
    This class provides methods to encode messages into formatted strings
    and decode raw messages into their constituent parts.

Raises:
    ValueError: Raised when the message format is incorrect during decoding.

Returns:
    tuple: A tuple containing the name, group, and message parts extracted
    from the raw message.
"""

class BasicBuilder:
    """
    A builder class for encoding and decoding messages.

    Summary:
        This class provides methods to encode messages into formatted strings
        and decode raw messages into their constituent parts.

    Methods:
        encode: Encodes a message into a formatted string.
        decode: Decodes a raw message into its constituent parts.
    """
    def __init__(self):
        pass

    def encode(self, name, group, msg):
        """
        Encodes a message into a formatted string.

        Args:
            name (str): The name of the message.
            group (str): The group of the message.
            msg (str): The text content of the message.

        Returns:
            str: The encoded message header with payload.
        """
        payload = f"{group},{name},{msg}"
        header = f"{len(payload):04d},{payload}"
        return header

    def decode(self, raw):
        """
        Decodes a raw message into its constituent parts.

        Args:
            raw (str): The raw message string to decode.

        Raises:
            ValueError: If the message format is incorrect.

        Returns:
            tuple: A tuple containing the name, group, and message parts
            extracted from the raw message.
        """
        parts = raw.split(",", 4)
        if len(parts) != 4:
            raise ValueError(f"message format error: {raw}")
        return parts[1], parts[2], parts[3]
