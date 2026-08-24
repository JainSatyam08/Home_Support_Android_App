package com.example.homesupport.components.cancelrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homeservices.ui.cancelrequest.CancelReasonOption
import com.example.homesupport.ui.theme.BorderGray
import com.example.homesupport.ui.theme.BrandGreen
import com.example.homesupport.ui.theme.TextDark
import com.example.homesupport.ui.theme.TextMuted

@Composable
fun CancellationReasonSection(
    options: List<CancelReasonOption>,
    selectedReasonId: String,
    onReasonSelected: (String) -> Unit,
    otherReasonText: String,
    onOtherReasonChanged: (String) -> Unit,
    maxReasonLength: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Cancellation Reason",
            color = TextDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Please tell us why you want to cancel this request.",
            color = TextMuted,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        ReasonOptionsList(
            options = options,
            selectedReasonId = selectedReasonId,
            onReasonSelected = onReasonSelected
        )

        if (selectedReasonId == "other") {
            Spacer(modifier = Modifier.height(16.dp))
            OtherReasonTextField(
                value = otherReasonText,
                onValueChange = onOtherReasonChanged,
                maxLength = maxReasonLength
            )
        }
    }
}

@Composable
fun ReasonOptionsList(
    options: List<CancelReasonOption>,
    selectedReasonId: String,
    onReasonSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, BorderGray, RoundedCornerShape(14.dp))
    ) {
        options.forEachIndexed { index, option ->
            ReasonRadioItem(
                option = option,
                selected = option.id == selectedReasonId,
                onClick = { onReasonSelected(option.id) }
            )
            if (index != options.lastIndex) {
                HorizontalDivider(color = BorderGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun ReasonRadioItem(
    option: CancelReasonOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option.label,
            color = TextDark,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )

        RadioButton(
            selected = selected,
            onClick = null, // onClick is handled by the Row's selectable modifier
            colors = RadioButtonDefaults.colors(selectedColor = BrandGreen)
        )
    }
}

/* ---------------------------------------------------------
 * "Please specify the reason" text field with char counter
 * --------------------------------------------------------- */
@Composable
private fun OtherReasonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Please specify the reason",
            color = TextMuted,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            placeholder = { Text("Type your reason here") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BrandGreen,
                unfocusedBorderColor = BorderGray
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions.Default
        )

        Text(
            text = "${value.length}/$maxLength",
            color = TextMuted,
            fontSize = 12.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}
