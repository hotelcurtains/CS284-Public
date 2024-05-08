package hw1;

public class BinaryNumber{
    /**
     * The value of the BinaryNumber. Each cell is only allowed to contain ints 1 or 0.
     */
    private int[] value;
    /**
     * The amounts of bits in the BinaryNumber. 
     */
    private int length;

    /** 
     * Construct a new BinaryNumber filled with zeros.
     * 
     * @param length The length of the inner array int[] value.
     * @throws IllegalArgumentException if given a negative length argument.
     */
    public BinaryNumber(int length){
        if (length < 0){
            throw new IllegalArgumentException("Cannot have a byte of negative length.");
        }
        value = new int[length];
        this.length = length;
    }

    /**
     * Construct a new BinaryNumber with a value equal to the input byte.
     * 
     * @param str The binary digit that the new BinaryNumber will be equal to.
     * @throws IllegalArgumentException if str argument contains anything except 1 and 0.
     */
    public BinaryNumber(String str){
        value = new int[str.length()];
        for (int i = 0; i<str.length(); i++){
            int current = Character.getNumericValue(str.charAt(i));
            if (current != 1 && current != 0){
                throw new IllegalArgumentException("A BinaryNumber can only contain 1s and 0s.");
            }        
            value[i] = current;
        }
        length = str.length();
    }

    /**
     * Gets the length of this BinaryNumber.
     * 
     * @return How many bits are in this BinaryNumber, including leading zeros.
     */
    public int getLength(){
        return length;
    }

    /**
     * Gets the value array of this BinaryNumber.
     * 
     * @return The value of this BinaryNumber, as an array with one byte in each cell.
     */
    public int[] getInnerArray(){
        return value;
    }

    /**
     * Gets the bit at the given index, zero-indexed.
     * 
     * @param index The index of the byte to get.
     * @return The bit at value[index].
     * @throws IndexOutOfBoundsException if the index is outside of the range of value.
     */
    public int getDigit(int index){
        try {
            return value[index];
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Gets the value of this BinaryNumber as represented in decimal notation. Returns 0 for empty byte.
     * 
     * @return the decimal value of this BinaryNumber.
     */
    public int toDecimal(){
        int output = 0;
        for (int i = 0; i < value.length; i++){
            output += value[i]*Math.pow(2,value.length-i-1);
        }
        return output;
    }

    /**
     * Moves this byte left or right.
     * Shifting left (direction = -1) will insert trailing zeros.
     * Shifting right (direction = 1) will truncate the byte.
     * Any other direction value will throw an IllegalArgumentException.
     * 
     * @param direction The direction in whcih to shift this byte. Either -1 (left) or 1 (right).
     * @param amount    The distance to shift this byte.
     * @throws IllegalArgumentException if direction argument is not equal to -1 or 1.
     */
    public void bitShift(int direction, int amount){
        if (direction == 1){
            int[] neo = new int[value.length-amount];
            for (int i = 0; i < neo.length; i++){
                neo[i] = value[i];
            }
            value = neo;
            length -= amount;
        } else if (direction == -1){
            int[] neo = new int[value.length+amount];
            for (int i = 0; i < value.length-1; i++){
                neo[i] = value[i];
            }
            value = neo;
            length += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Finds the OR of the values of two BinaryNumbers with the same length.
     * @param bn1 The first operand.
     * @param bn2 The second operand.
     * @return The disjunction of arguments bn1 and bn2, assuming 0 = F and 1 = T as an array of ints.
     * @throws IllegalArgumentException if arguments bn1 and bn2 are of different lengths.
     */
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
        int end = bn1.getLength();
        if (end != bn2.getLength()){
            throw new IllegalArgumentException("Both arguments must be of the same length.");
        }
        int[] out = new int[end];
        for (int i = 0; i < end; i++){
            if (bn1.getDigit(i) + bn2.getDigit(i) >= 1){
                out[i] = 1;
            }
        }
        return out;
    }

    /**
     * Finds the AND of the values of two BinaryNumbers with the same length.
     * @param bn1 The first operand.
     * @param bn2 The second operand.
     * @return The conjunction of arguments bn1 and bn2, assuming 0 = F and 1 = T as an array of ints.
     * @throws IllegalArgumentException if arguments bn1 and bn2 are of different lengths.
     */
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2){
        int end = bn1.getLength();
        if (end != bn2.getLength()){
            throw new IllegalArgumentException();
        }
        int[] out = new int[end];
        for (int i = 0; i < bn1.getLength(); i++){
            if (bn1.getDigit(i) + bn2.getDigit(i) == 2){
                out[i] = 1;
            }
        }
        return out;
    }

    /**
     * Gets the value of this BinaryNumber as a String. The output should look similar to the input for the String argument constructor.
     * @return The value of the BinaryNumber.
     */
    public String toString(){
        String out = "";
        for (int i=0; i<value.length; i++){
            out+=value[i];
        }
        return out;
    }

    /**
     * Adds the argument aBinaryNumber to the given BinaryNumber in place. 
     * Will have the same length as the longer of the given BinaryNumber or aBinaryNumber and may be one bit longer, depending on the value of their sum.
     * @param aBinaryNumber The value to add
     */
    public void add(BinaryNumber aBinaryNumber){
        int[] current = value;
        int[] other = aBinaryNumber.getInnerArray();
        int size = Math.max(current.length, other.length);
        current = contain(current, size);
        other = contain(other, size);

        int carry = 0;
        for (int i = size-1; i >= 0; i--) {
            if (current[i] + other[i] + carry == 0){
                current[i] = 0;
                carry = 0;
            } else if (current[i] + other[i] + carry == 1){
                current[i] = 1;
                carry = 0;
            } else if (current[i] + other[i] + carry == 2){
                current[i] = 0;
                carry = 1;
            } else if (current[i] + other[i] + carry == 3){
                current[i] = 1;
                carry = 1;
            }
        }
        if(carry == 1){
            current = contain(current, size+1); 
            current[0] = 1;
            length++;
        }
        value = current;
    }

    /**
     * Truncates byte or adds leading zeros to set the given int array to the byte size given.
     * @param input The byte to resize.
     * @param bits The size of the new byte.
     * @return A new array of ints with the value of the input argument with a size equal to the bits argument.
     */
    public static int[] contain(int[] input, int bits){
        int[] neo = new int[bits];
        int stop = 0;
        if (bits < input.length){
            stop = bits;
        } else {
            stop = input.length;
        }
        for (int i = 0; i < stop; i++){
            neo[bits-i-1] = input[input.length-i-1];
        }
        return neo;
    }
}